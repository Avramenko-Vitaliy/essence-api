CREATE TABLE brands (
    id SERIAL CONSTRAINT brands_pk PRIMARY KEY,
    name VARCHAR NOT NULL,
    logo_id UUID NOT NULL
);

CREATE TABLE category_types (
    id INT CONSTRAINT category_types_pk PRIMARY KEY,
    api_key VARCHAR NOT NULL
);
CREATE UNIQUE INDEX category_types_api_key_uindex ON category_types (api_key);

INSERT INTO category_types(id, api_key)
VALUES (1, 'CLOTHING'),
       (2, 'SHOES'),
       (3, 'ACCESSORIES');

CREATE TABLE sex_types (
    id INT CONSTRAINT sex_types_pk PRIMARY KEY,
    api_key VARCHAR NOT NULL
);
CREATE UNIQUE INDEX sex_types_api_key_uindex ON sex_types (api_key);

INSERT INTO sex_types(id, api_key)
VALUES (1, 'MEN'),
       (2, 'WOMEN');

CREATE TABLE size_types (
    id INT CONSTRAINT size_types_pk PRIMARY KEY,
    api_key VARCHAR NOT NULL
);
CREATE UNIQUE INDEX size_types_api_key_uindex ON size_types (api_key);

INSERT INTO size_types(id, api_key)
VALUES (1, 'XL'),
       (2, 'X'),
       (3, 'M'),
       (4, 'S');

CREATE TABLE color_types (
    id INT CONSTRAINT color_types_pk PRIMARY KEY,
    api_key VARCHAR NOT NULL
);
CREATE UNIQUE INDEX color_types_api_key_uindex ON color_types (api_key);

INSERT INTO color_types(id, api_key)
VALUES (1, 'BLACK'),
       (2, 'WHITE'),
       (3, 'RED'),
       (4, 'PURPLE');

CREATE TABLE clothing_types (
    id INT CONSTRAINT clothing_types_pk PRIMARY KEY,
    api_key VARCHAR NOT NULL
);
CREATE UNIQUE INDEX clothing_types_api_key_uindex ON clothing_types (api_key);

INSERT INTO clothing_types(id, api_key)
VALUES (1, 'DRESSES'),
       (2, 'BLOUSES'),
       (3, 'SHIRTS'),
       (4, 'T_SHIRT'),
       (5, 'ROMPERS'),
       (6, 'BRAS'),
       (7, 'PANTIES'),
       (8, 'POLO'),
       (9, 'JACKETS'),
       (10, 'TRENCH');

CREATE TABLE products (
    id SERIAL CONSTRAINT products_pk PRIMARY KEY,
    title VARCHAR NOT NULL,
    description VARCHAR NOT NULL,
    price NUMERIC(16, 2) NOT NULL,
    discount NUMERIC(16, 2) DEFAULT 0 NOT NULL,
    for_kids BOOLEAN DEFAULT FALSE NOT NULL,
    sex_type_id INT NOT NULL CONSTRAINT products_sex_types_id_fk
        REFERENCES sex_types (id) ON UPDATE RESTRICT ON DELETE RESTRICT,
    clothing_type_id INT NOT NULL CONSTRAINT products_clothing_types_id_fk
        REFERENCES clothing_types (id) ON UPDATE RESTRICT ON DELETE RESTRICT,
    category_type_id INT NOT NULL CONSTRAINT products_category_types_id_fk
        REFERENCES category_types (id) ON UPDATE RESTRICT ON DELETE RESTRICT,
    brand_id INT NOT NULL CONSTRAINT products_brands_id_fk
        REFERENCES brands (id) ON UPDATE RESTRICT ON DELETE RESTRICT,
    creation_date TIMESTAMP WITHOUT TIME ZONE DEFAULT now() NOT NULL,
    update_date TIMESTAMP WITHOUT TIME ZONE DEFAULT now() NOT NULL
);

CREATE TABLE products_sizes (
    product_id INT NOT NULL CONSTRAINT products_sizes_products_id_fk
        REFERENCES products ON UPDATE CASCADE ON DELETE CASCADE,
    size_id INT NOT NULL CONSTRAINT products_sizes_size_types_id_fk
        REFERENCES size_types ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT products_sizes_pk PRIMARY KEY (product_id, size_id)
);

CREATE TABLE products_colors (
    product_id INT NOT NULL CONSTRAINT products_colors_products_id_fk
        REFERENCES products ON UPDATE CASCADE ON DELETE CASCADE,
    color_id INT NOT NULL CONSTRAINT products_colors_color_types_id_fk
        REFERENCES color_types ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT products_colors_pk PRIMARY KEY (product_id, color_id)
);

CREATE TABLE product_images (
    id SERIAL CONSTRAINT product_images_pk PRIMARY KEY,
    product_id INT NOT NULL CONSTRAINT product_images_products_id_fk
        REFERENCES products (id) ON UPDATE CASCADE ON DELETE CASCADE,
    image_id UUID NOT NULL
);
