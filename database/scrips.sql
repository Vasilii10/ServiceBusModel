create table deliveryrequest
(
    delivery_request_id    bigserial not null
        constraint delivery_request_pkey
            primary key,
    departure_point        varchar(255),
    destination_point      varchar(255),
    request_service_status varchar,
    shipment_method        varchar,
    delivery_track_number  varchar(255)
);

create unique index deliveryrequest_delivery_track_number_uindex
    on deliveryrequest (delivery_track_number);
