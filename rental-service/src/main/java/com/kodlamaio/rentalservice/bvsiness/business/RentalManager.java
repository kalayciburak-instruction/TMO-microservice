package com.kodlamaio.rentalservice.bvsiness.business;

import com.kodlamaio.commonpackage.events.rental.RentalCreatedEvent;
import com.kodlamaio.commonpackage.events.rental.RentalDeletedEvent;
import com.kodlamaio.commonpackage.kafka.producer.KafkaProducer;
import com.kodlamaio.commonpackage.utils.dto.CreateCustomerRequest;
import com.kodlamaio.commonpackage.utils.dto.CreateRentalPaymentRequest;
import com.kodlamaio.commonpackage.utils.jwt.customer.ParseJwtCustomerInfo;
import com.kodlamaio.commonpackage.utils.mappers.ModelMapperService;
import com.kodlamaio.rentalservice.api.client.CustomerClient;
import com.kodlamaio.rentalservice.bvsiness.abstracts.RentalService;
import com.kodlamaio.rentalservice.bvsiness.dto.requests.CreateRentalRequest;
import com.kodlamaio.rentalservice.bvsiness.dto.requests.UpdateRentalRequest;
import com.kodlamaio.rentalservice.bvsiness.dto.responses.CreateRentalResponse;
import com.kodlamaio.rentalservice.bvsiness.dto.responses.GetAllRentalsResponse;
import com.kodlamaio.rentalservice.bvsiness.dto.responses.GetRentalResponse;
import com.kodlamaio.rentalservice.bvsiness.dto.responses.UpdateRentalResponse;
import com.kodlamaio.rentalservice.bvsiness.rules.RentalBusinessrules;
import com.kodlamaio.rentalservice.entities.Rental;
import com.kodlamaio.rentalservice.repository.RentalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RentalManager implements RentalService {
    private final RentalRepository repository;
    private final ModelMapperService mapper;
    private final RentalBusinessrules rules;
    private final KafkaProducer producer;
    private final CustomerClient customerClient;

    @Override
    public List<GetAllRentalsResponse> getAll() {
        var rentals = repository.findAll();
        var response = rentals
                .stream()
                .map(rental -> mapper.forResponse().map(rental, GetAllRentalsResponse.class))
                .toList();

        return response;
    }

    @Override
    public GetRentalResponse getById(UUID id) {
        rules.checkIfRentalExists(id);
        var rental = repository.findById(id).orElseThrow();
        var response = mapper.forResponse().map(rental, GetRentalResponse.class);

        return response;
    }

    @Override
    public CreateRentalResponse add(CreateRentalRequest request, Jwt jwt) {
        //? Keycloak veritabanından kendi postgre veritabanımıza kayıt ediyoruz.
        var customerRequest = setCustomerInfoFromJwt(jwt);
        customerClient.saveCustomer(customerRequest);

        rules.ensureCarIsAvailable(request.getCarId());
        var rental = mapper.forRequest().map(request, Rental.class);
        rental.setId(null);
        rental.setCustomerId(UUID.fromString(jwt.getSubject()));
        rental.setTotalPrice(getTotalPrice(rental));
        rental.setRentedAt(LocalDate.now());

        var paymentRequest = new CreateRentalPaymentRequest();
        mapper.forRequest().map(request.getInfo(), paymentRequest);
        paymentRequest.setPrice(getTotalPrice(rental));
        rules.ensurePayment(paymentRequest);

        repository.save(rental);
        sendKafkaRentalCreatedEvent(request.getCarId());
        var response = mapper.forResponse().map(rental, CreateRentalResponse.class);

        return response;
    }

    @Override
    public UpdateRentalResponse update(UUID id, UpdateRentalRequest request) {
        rules.checkIfRentalExists(id);
        var rental = mapper.forRequest().map(request, Rental.class);
        rental.setId(id);
        rental.setTotalPrice(getTotalPrice(rental));
        repository.save(rental);
        var response = mapper.forResponse().map(rental, UpdateRentalResponse.class);

        return response;
    }

    @Override
    public void delete(UUID id) {
        rules.checkIfRentalExists(id);
        sendKafkaRentalDeletedEvent(id);
        repository.deleteById(id);
    }

    private double getTotalPrice(Rental rental) {
        return rental.getDailyPrice() * rental.getRentedForDays();
    }

    private void sendKafkaRentalCreatedEvent(UUID carId) {
        producer.sendMessage(new RentalCreatedEvent(carId), "rental-created");
    }

    private void sendKafkaRentalDeletedEvent(UUID id) {
        var carId = repository.findById(id).orElseThrow().getCarId();
        producer.sendMessage(new RentalDeletedEvent(carId), "rental-deleted");
    }

    private CreateCustomerRequest setCustomerInfoFromJwt(Jwt jwt) {
        var customerRequest = new CreateCustomerRequest();
        customerRequest.setId(ParseJwtCustomerInfo.getCustomerInformation(jwt).getId());
        customerRequest.setFirstName(ParseJwtCustomerInfo.getCustomerInformation(jwt).getFirstName());
        customerRequest.setLastName(ParseJwtCustomerInfo.getCustomerInformation(jwt).getLastName());
        customerRequest.setUsername(ParseJwtCustomerInfo.getCustomerInformation(jwt).getUsername());
        customerRequest.setEmail(ParseJwtCustomerInfo.getCustomerInformation(jwt).getEmail());
        customerClient.saveCustomer(customerRequest);

        return customerRequest;
    }
}
