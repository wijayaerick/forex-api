--
-- PostgreSQL database dump
--

-- Dumped from database version 10.8
-- Dumped by pg_dump version 10.8

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: currency_pair; Type: TABLE; Schema: public; Owner: forex
--

CREATE TABLE public.currency_pair (
    id bigint NOT NULL,
    base_currency character varying(255),
    quote_currency character varying(255)
);


ALTER TABLE public.currency_pair OWNER TO forex;

--
-- Name: exchange_rate; Type: TABLE; Schema: public; Owner: forex
--

CREATE TABLE public.exchange_rate (
    id bigint NOT NULL,
    date date,
    rate double precision NOT NULL,
    currency_pair_id bigint
);


ALTER TABLE public.exchange_rate OWNER TO forex;

--
-- Name: hibernate_sequence; Type: SEQUENCE; Schema: public; Owner: forex
--

CREATE SEQUENCE public.hibernate_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.hibernate_sequence OWNER TO forex;

--
-- Name: hibernate_sequences; Type: TABLE; Schema: public; Owner: forex
--

CREATE TABLE public.hibernate_sequences (
    sequence_name character varying(255) NOT NULL,
    next_val bigint
);


ALTER TABLE public.hibernate_sequences OWNER TO forex;

--
-- Data for Name: currency_pair; Type: TABLE DATA; Schema: public; Owner: forex
--

COPY public.currency_pair (id, base_currency, quote_currency) FROM stdin;
\.


--
-- Data for Name: exchange_rate; Type: TABLE DATA; Schema: public; Owner: forex
--

COPY public.exchange_rate (id, date, rate, currency_pair_id) FROM stdin;
\.


--
-- Data for Name: hibernate_sequences; Type: TABLE DATA; Schema: public; Owner: forex
--

COPY public.hibernate_sequences (sequence_name, next_val) FROM stdin;
default	0
\.


--
-- Name: hibernate_sequence; Type: SEQUENCE SET; Schema: public; Owner: forex
--

SELECT pg_catalog.setval('public.hibernate_sequence', 1, false);


--
-- Name: currency_pair currency_pair_pkey; Type: CONSTRAINT; Schema: public; Owner: forex
--

ALTER TABLE ONLY public.currency_pair
    ADD CONSTRAINT currency_pair_pkey PRIMARY KEY (id);


--
-- Name: exchange_rate exchange_rate_pkey; Type: CONSTRAINT; Schema: public; Owner: forex
--

ALTER TABLE ONLY public.exchange_rate
    ADD CONSTRAINT exchange_rate_pkey PRIMARY KEY (id);


--
-- Name: hibernate_sequences hibernate_sequences_pkey; Type: CONSTRAINT; Schema: public; Owner: forex
--

ALTER TABLE ONLY public.hibernate_sequences
    ADD CONSTRAINT hibernate_sequences_pkey PRIMARY KEY (sequence_name);


--
-- Name: exchange_rate fkd51j97oh3j3nnjwxtf9n8rx5c; Type: FK CONSTRAINT; Schema: public; Owner: forex
--

ALTER TABLE ONLY public.exchange_rate
    ADD CONSTRAINT fkd51j97oh3j3nnjwxtf9n8rx5c FOREIGN KEY (currency_pair_id) REFERENCES public.currency_pair(id);


--
-- PostgreSQL database dump complete
--

