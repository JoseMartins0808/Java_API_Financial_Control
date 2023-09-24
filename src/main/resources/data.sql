INSERT INTO "users" ("name", "cpf", "email", "password", "type")
VALUES
    ('José Martins', '87311768920', 'jose@mail.com', '1234', 'COMMON'),
    ('Simone Kregeroski', '62566688820', 'simone@mail.com', '1234', 'SELLER');

INSERT INTO "transactions" ("payer_id", "payee_id", "value")
VALUES
    ('1', '2', 25.5);