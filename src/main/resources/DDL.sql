CREATE DATABASE receipt; 

\c receipt;

CREATE TABLE public.products ( 
    id SERIAL PRIMARY KEY, 
    name character varying(255), 
    price double precision, 
    status character varying(255) 
);

CREATE TABLE public.discount_cards ( 
    id SERIAL PRIMARY KEY,
    percent_discount double precision
);

CREATE TABLE public.baskets ( 
    id SERIAL PRIMARY KEY, 
    price double precision, 
    discount_card_id integer REFERENCES public.discount_cards (id) 
);

CREATE TABLE public.basket_items ( 
    id SERIAL PRIMARY KEY, 
    count integer, 
    basket_id integer REFERENCES public.baskets (id)  ON DELETE CASCADE, 
    product_id integer  REFERENCES public.products (id)
);