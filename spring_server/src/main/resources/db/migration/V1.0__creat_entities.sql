CREATE TABLE IF NOT EXISTS users
(
    id       BIGSERIAL PRIMARY KEY NOT NULL,
    email    TEXT NOT NULL UNIQUE,
    password TEXT NOT NULL,
    role     TEXT NOT NULL DEFAULT 'USER',
    created_at TIMESTAMP with time zone DEFAULT now(),
    updated_at TIMESTAMP with time zone DEFAULT now()
);
--

-- CREATE TABLE roles
-- (
--     id   BIGSERIAL PRIMARY KEY NOT NULL,
--     name TEXT                  NOT NULL UNIQUE
-- );
--

-- CREATE TABLE user_roles
-- (
--     user_id   BIGSERIAL  NOT NULL,
--     role_id   BIGSERIAL  NOT NULL,
--     FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE ON UPDATE RESTRICT,
--     FOREIGN KEY (role_id) REFERENCES roles(id) ON DELETE CASCADE ON UPDATE RESTRICT
-- );
--

CREATE TABLE types
(
    id          BIGSERIAL PRIMARY KEY NOT NULL,
    name        TEXT NOT NULL,
    created_at   TIMESTAMP with time zone DEFAULT now(),
    updated_at   TIMESTAMP with time zone DEFAULT now()
);
--

CREATE TABLE brands
(
    id          BIGSERIAL PRIMARY KEY NOT NULL,
    name        TEXT NOT NULL,
    created_at   TIMESTAMP with time zone DEFAULT now(),
    updated_at   TIMESTAMP with time zone DEFAULT now()
);
--

CREATE TABLE devices
(
    id          BIGSERIAL PRIMARY KEY NOT NULL,
    name        TEXT NOT NULL UNIQUE ,
    price       INTEGER NOT NULL,
    rating      INTEGER DEFAULT 0,
    img         TEXT NOT NULL,
    type_id     BIGSERIAL NOT NULL,
    brand_id    BIGSERIAL NOT NULL,
    created_at  TIMESTAMP with time zone DEFAULT now(),
    updated_at  TIMESTAMP with time zone DEFAULT now(),
        FOREIGN KEY (type_id) REFERENCES types(id),
        FOREIGN KEY (brand_id) REFERENCES brands(id)
);
--

CREATE TABLE baskets (
    id              BIGSERIAL PRIMARY KEY,
    user_id         BIGSERIAL NOT NULL,
        FOREIGN KEY (user_id) REFERENCES users(id),
    created_at  TIMESTAMP with time zone DEFAULT now(),
    updated_at  TIMESTAMP with time zone DEFAULT now()
);
--

CREATE TABLE basket_devices
(
    id           BIGSERIAL PRIMARY KEY,
    device_id    BIGSERIAL NOT NULL,
        FOREIGN KEY (device_id) REFERENCES devices(id),
    basket_id    BIGSERIAL NOT NULL,
        FOREIGN KEY (basket_id) REFERENCES baskets(id),
    created_at   TIMESTAMP with time zone DEFAULT now(),
    updated_at   TIMESTAMP with time zone DEFAULT now()
);
--

CREATE TABLE type_brands
(
    id           BIGSERIAL PRIMARY KEY,
    type_id      BIGSERIAL,
    brand_id     BIGSERIAL,
        FOREIGN KEY (type_id) REFERENCES types(id),
        FOREIGN KEY (brand_id) REFERENCES brands(id)
);
--

CREATE TABLE device_infos
(
    id                   BIGSERIAL PRIMARY KEY NOT NULL ,
    device_id            BIGSERIAL NOT NULL,
    title                TEXT NOT NULL UNIQUE ,
    description          TEXT,
    created_at  TIMESTAMP with time zone DEFAULT now(),
    updated_at  TIMESTAMP with time zone DEFAULT now(),
        FOREIGN KEY (device_id) REFERENCES devices(id)
);
--

CREATE TABLE ratings
(
    id          BIGSERIAL PRIMARY KEY NOT NULL ,
    user_id     BIGSERIAL NOT NULL,
    device_id   BIGSERIAL NOT NULL,
    rate        INTEGER NOT NULL,
    created_at  TIMESTAMP with time zone DEFAULT now(),
    updated_at  TIMESTAMP with time zone DEFAULT now(),
        FOREIGN KEY (user_id) REFERENCES users(id),
        FOREIGN KEY (device_id) REFERENCES devices(id)
);

ALTER TABLE users
    ALTER COLUMN role SET DEFAULT 'USER';

ALTER TABLE users
    ADD COLUMN status TEXT NOT NULL DEFAULT 'ACTIVE';
