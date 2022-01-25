create table banks
(
    bank_id uuid primary key
);

create table credit_details
(
    credit_details_id uuid primary key,
    credit_limit      bigint,
    credit_percent    numeric(5, 2),
    bank_id           uuid references banks (bank_id),
    primary key (credit_details_id)
);

create table payment_schedule
(
    payment_schedule_id    uuid primary key,
    date_of_first_payment  timestamp,
    date_of_last_payment   timestamp,
    sum_of_monthly_payment numeric(12, 2),
    sum_of_payment         numeric(12, 2),
    sum_of_percent         numeric(12, 2),
    sum_of_principal       numeric(12, 2)

);

create table clients
(
    client_id        uuid primary key not null,
    email            varchar(255),
    passport_number  bigint,
    telephone_number bigint,
    bank_id          uuid references banks (bank_id)

);

create table credit_offer
(
    credit_offer_id     uuid primary key,
    sum_of_credit       numeric(12, 2),
    client_id           uuid references clients (client_id),
    months_of_credit    int,
    credit_details_id   uuid references credit_details (credit_details_id),
    payment_schedule_id uuid references payment_schedule (payment_schedule_id)

);

-- alter table payment_schedule add constraint fk_payment_schedule_credit_offer foreign key (credit_offer_id)
--     references credit_offer(credit_offer_id);
--
-- alter table credit_offer add constraint fk_credit_offer_payment_schedule foreign key (payment_schedule_id)
--     references payment_schedule(payment_schedule_id);

