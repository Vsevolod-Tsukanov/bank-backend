insert into banks(bank_id)
values ('ae1ce5c1-b1eb-4ee7-a1a2-63d831b0fd0a');
insert into banks(bank_id)
values ('efe4feed-38e4-4a55-8215-c03d12c5ab22');
insert into banks(bank_id)
values ('218a0903-c8fd-4f15-a81f-f65ed16a9a50');

insert into clients(client_id, email, passport_number, telephone_number, bank_id)
values ('cb6dc13e-4c30-4c59-a502-1257cb4ad869', 'first@mail.ru', 123456, 89202223334,
        'ae1ce5c1-b1eb-4ee7-a1a2-63d831b0fd0a');

insert into clients(client_id, email, passport_number, telephone_number, bank_id)
values ('e6ece8c7-6d69-477f-b511-62ac525b397d', 'second@mail.ru', 654321, 89501254554,
        'efe4feed-38e4-4a55-8215-c03d12c5ab22');

insert into clients(client_id, email, passport_number, telephone_number, bank_id)
values ('d7abc2db-1886-46fc-aa26-35fdcf5c07ee', 'third@mail.ru', 2468642, 89101112233,
        '218a0903-c8fd-4f15-a81f-f65ed16a9a50');



insert into payment_schedule(payment_schedule_id, date_of_first_payment, date_of_last_payment, sum_of_payment,
                             sum_of_monthly_payment, sum_of_percent, sum_of_principal)
values ('63d40407-50b0-4e4e-bceb-354a1d3341e9', '2022-02-25', '2022-11-25', 10100, 1010, 1100, 10000);

insert into payment_schedule(payment_schedule_id, date_of_first_payment, date_of_last_payment, sum_of_payment,
                             sum_of_monthly_payment, sum_of_percent, sum_of_principal)
values ('ce37d0d1-e103-4a20-8ec9-e7d46f28daf7', '2022-02-25', '2022-11-25', 154000, 15400, 14000, 140000);

insert into payment_schedule(payment_schedule_id, date_of_first_payment, date_of_last_payment, sum_of_payment,
                             sum_of_monthly_payment, sum_of_percent, sum_of_principal)
values ('4e636996-be96-4f86-987c-b80c6c727316', '2022-02-25', '2022-11-25', 1680000, 168000, 280000, 1400000);



insert into credit_details(credit_details_id, credit_limit, credit_percent, bank_id)
values ('9b9df329-787b-4082-b9ef-2e6b2fd16917', 15000, 1, 'ae1ce5c1-b1eb-4ee7-a1a2-63d831b0fd0a');

insert into credit_details(credit_details_id, credit_limit, credit_percent, bank_id)
values ('f5a8cc65-4a23-4bee-ac42-c5d2c8f4ef62', 150000, 10, 'efe4feed-38e4-4a55-8215-c03d12c5ab22');

insert into credit_details(credit_details_id, credit_limit, credit_percent, bank_id)
values ('a4fbac7e-5026-40ac-a1b2-855558c074ec', 1500000, 20, '218a0903-c8fd-4f15-a81f-f65ed16a9a50');



insert into credit_offer(credit_offer_id, sum_of_credit, client_id, months_of_credit, credit_details_id,
                         payment_schedule_id)
values ('91a33e6f-152a-4baf-8385-4e9fc2fe0059', 1000, 'cb6dc13e-4c30-4c59-a502-1257cb4ad869', 10,
        '9b9df329-787b-4082-b9ef-2e6b2fd16917', '63d40407-50b0-4e4e-bceb-354a1d3341e9');

insert into credit_offer(credit_offer_id, sum_of_credit, client_id, months_of_credit, credit_details_id,
                         payment_schedule_id)
values ('a7c82030-7283-47e2-ad8d-d24dfe44d1b7', 10000, 'e6ece8c7-6d69-477f-b511-62ac525b397d', 12,
        'f5a8cc65-4a23-4bee-ac42-c5d2c8f4ef62', 'ce37d0d1-e103-4a20-8ec9-e7d46f28daf7');

insert into credit_offer(credit_offer_id, sum_of_credit, client_id, months_of_credit, credit_details_id,
                         payment_schedule_id)
values ('45671fe4-cd57-400f-85bf-ac0b7a0a049f', 12000, 'd7abc2db-1886-46fc-aa26-35fdcf5c07ee', 20,
        'a4fbac7e-5026-40ac-a1b2-855558c074ec', '4e636996-be96-4f86-987c-b80c6c727316');