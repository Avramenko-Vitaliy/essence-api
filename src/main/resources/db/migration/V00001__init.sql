CREATE TABLE users (
    id UUID CONSTRAINT users_pk PRIMARY KEY,
    first_name VARCHAR NOT NULL,
    last_name VARCHAR NOT NULL,
    password VARCHAR NOT NULL,
    email VARCHAR NOT NULL
);

CREATE UNIQUE INDEX users_email_uindex ON users (email);

CREATE TABLE roles (
    id INT CONSTRAINT roles_pk PRIMARY KEY,
    api_key VARCHAR NOT NULL
);
CREATE UNIQUE INDEX roles_api_key_uindex ON roles (api_key);

INSERT INTO roles (id, api_key)
VALUES (1, 'ADMIN'),
       (2, 'USER');

CREATE TABLE permissions (
    id INT CONSTRAINT permissions_pk PRIMARY KEY,
    api_key VARCHAR NOT NULL
);
CREATE UNIQUE INDEX permissions_api_key_uindex ON permissions (api_key);

INSERT INTO permissions(id, api_key)
VALUES (1, 'VIEW_PRODUCTS'),
       (2, 'MANAGE_PRODUCTS');

CREATE TABLE roles_permissions (
    role_id INT NOT NULL CONSTRAINT roles_permissions_roles_id_fk REFERENCES roles (id) ON UPDATE CASCADE ON DELETE CASCADE,
    permission_id INT NOT NULL CONSTRAINT roles_permissions_permissions_id_fk REFERENCES permissions (id) ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT roles_permissions_pk PRIMARY KEY (role_id, permission_id)
);

INSERT INTO roles_permissions(role_id, permission_id)
VALUES (1, 1),
       (1, 2),
       (2, 1);

ALTER TABLE users ADD role_id INT;
ALTER TABLE users ADD CONSTRAINT users_roles_id_fk FOREIGN KEY (role_id) REFERENCES roles ON UPDATE RESTRICT ON DELETE RESTRICT;
UPDATE users SET role_id = 2;
ALTER TABLE users ALTER COLUMN role_id SET NOT NULL;

INSERT INTO users(id, first_name, last_name, password, email, role_id)
VALUES ('00000000-0000-0000-0000-000000000001', 'John', 'Doe', '$2a$10$9YsE5syI/AFi6gaK4dtLT.k.CusUZyQHmcHM1eKqj8gqlGhI4BFXK', 'test@test.com', 1);
