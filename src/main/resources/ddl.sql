SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 185 (class 1259 OID 49152)
-- Name: category; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE category (
    id bigint NOT NULL,
    name character varying(255)
);


ALTER TABLE category OWNER TO postgres;

--
-- TOC entry 186 (class 1259 OID 49157)
-- Name: comments; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE comments (
    id bigint NOT NULL,
    insert_time bytea,
    mark integer,
    id_restaurant bigint,
    id_user bigint
);


ALTER TABLE comments OWNER TO postgres;

--
-- TOC entry 194 (class 1259 OID 49212)
-- Name: hibernate_sequence; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE hibernate_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE hibernate_sequence OWNER TO postgres;

--
-- TOC entry 187 (class 1259 OID 49165)
-- Name: locations; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE locations (
    id bigint NOT NULL,
    name character varying(255),
    number integer
);


ALTER TABLE locations OWNER TO postgres;

--
-- TOC entry 188 (class 1259 OID 49170)
-- Name: meal; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE meal (
    id bigint NOT NULL,
    description character varying(255),
    name character varying(255),
    price double precision,
    type character varying(255),
    id_restaurant bigint
);


ALTER TABLE meal OWNER TO postgres;

--
-- TOC entry 189 (class 1259 OID 49178)
-- Name: res_cat; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE res_cat (
    res_id bigint NOT NULL,
    cat_id bigint NOT NULL
);


ALTER TABLE res_cat OWNER TO postgres;

--
-- TOC entry 190 (class 1259 OID 49183)
-- Name: reservations; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE reservations (
    id bigint NOT NULL,
    persons integer,
    reservation_date_time bytea,
    id_table bigint,
    id_user bigint
);


ALTER TABLE reservations OWNER TO postgres;

--
-- TOC entry 191 (class 1259 OID 49191)
-- Name: restaurants; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE restaurants (
    id bigint NOT NULL,
    cover_file_name character varying(255),
    description character varying(255),
    food_type character varying(255),
    image_file_name character varying(255),
    latitude double precision,
    longitude double precision,
    mark integer,
    price_range integer,
    restaurant_name character varying(255),
    votes integer,
    location bigint
);


ALTER TABLE restaurants OWNER TO postgres;

--
-- TOC entry 192 (class 1259 OID 49199)
-- Name: tables; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE tables (
    id bigint NOT NULL,
    sitting_places integer,
    id_restaurant bigint
);


ALTER TABLE tables OWNER TO postgres;

--
-- TOC entry 193 (class 1259 OID 49204)
-- Name: users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE users (
    id bigint NOT NULL,
    city character varying(255),
    country character varying(255),
    email character varying(255),
    first_name character varying(255),
    last_name character varying(255),
    password character varying(255),
    phone character varying(255)
);


ALTER TABLE users OWNER TO postgres;

--
-- TOC entry 2039 (class 2606 OID 49156)
-- Name: category category_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY category
    ADD CONSTRAINT category_pkey PRIMARY KEY (id);


--
-- TOC entry 2041 (class 2606 OID 49164)
-- Name: comments comments_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY comments
    ADD CONSTRAINT comments_pkey PRIMARY KEY (id);


--
-- TOC entry 2043 (class 2606 OID 49169)
-- Name: locations locations_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY locations
    ADD CONSTRAINT locations_pkey PRIMARY KEY (id);


--
-- TOC entry 2045 (class 2606 OID 49177)
-- Name: meal meal_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY meal
    ADD CONSTRAINT meal_pkey PRIMARY KEY (id);


--
-- TOC entry 2047 (class 2606 OID 49182)
-- Name: res_cat res_cat_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY res_cat
    ADD CONSTRAINT res_cat_pkey PRIMARY KEY (res_id, cat_id);


--
-- TOC entry 2049 (class 2606 OID 49190)
-- Name: reservations reservations_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY reservations
    ADD CONSTRAINT reservations_pkey PRIMARY KEY (id);


--
-- TOC entry 2051 (class 2606 OID 49198)
-- Name: restaurants restaurants_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY restaurants
    ADD CONSTRAINT restaurants_pkey PRIMARY KEY (id);


--
-- TOC entry 2053 (class 2606 OID 49203)
-- Name: tables tables_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tables
    ADD CONSTRAINT tables_pkey PRIMARY KEY (id);


--
-- TOC entry 2055 (class 2606 OID 49211)
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- TOC entry 2057 (class 2606 OID 49219)
-- Name: comments fk2e1j871ildbkagpidsson8krk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY comments
    ADD CONSTRAINT fk2e1j871ildbkagpidsson8krk FOREIGN KEY (id_user) REFERENCES users(id);


--
-- TOC entry 2056 (class 2606 OID 49214)
-- Name: comments fk4y4y6ufptbjqvslamthefc9c; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY comments
    ADD CONSTRAINT fk4y4y6ufptbjqvslamthefc9c FOREIGN KEY (id_restaurant) REFERENCES restaurants(id);


--
-- TOC entry 2060 (class 2606 OID 49234)
-- Name: res_cat fk5e2opek59u0eiov0xdww4gyhj; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY res_cat
    ADD CONSTRAINT fk5e2opek59u0eiov0xdww4gyhj FOREIGN KEY (res_id) REFERENCES restaurants(id);


--
-- TOC entry 2063 (class 2606 OID 49249)
-- Name: restaurants fk60ivuqqdnpuvw6cgyc4ntuyr1; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY restaurants
    ADD CONSTRAINT fk60ivuqqdnpuvw6cgyc4ntuyr1 FOREIGN KEY (location) REFERENCES locations(id);


--
-- TOC entry 2062 (class 2606 OID 49244)
-- Name: reservations fkdiwd3klqyn3i4kyd5om7a4onv; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY reservations
    ADD CONSTRAINT fkdiwd3klqyn3i4kyd5om7a4onv FOREIGN KEY (id_user) REFERENCES users(id);


--
-- TOC entry 2059 (class 2606 OID 49229)
-- Name: res_cat fkds07arv33mpbirrkjw0fdd3tt; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY res_cat
    ADD CONSTRAINT fkds07arv33mpbirrkjw0fdd3tt FOREIGN KEY (cat_id) REFERENCES category(id);


--
-- TOC entry 2061 (class 2606 OID 49239)
-- Name: reservations fkmdkuun1evi4p1l2agoliqbkis; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY reservations
    ADD CONSTRAINT fkmdkuun1evi4p1l2agoliqbkis FOREIGN KEY (id_table) REFERENCES tables(id);


--
-- TOC entry 2064 (class 2606 OID 49254)
-- Name: tables fkoig810drvme71ag2v7kd4s58; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tables
    ADD CONSTRAINT fkoig810drvme71ag2v7kd4s58 FOREIGN KEY (id_restaurant) REFERENCES restaurants(id);


--
-- TOC entry 2058 (class 2606 OID 49224)
-- Name: meal fkrgm2yitugbckudg4t0a50tk7; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY meal
    ADD CONSTRAINT fkrgm2yitugbckudg4t0a50tk7 FOREIGN KEY (id_restaurant) REFERENCES restaurants(id);


