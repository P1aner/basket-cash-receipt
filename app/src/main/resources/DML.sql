INSERT INTO public.products (name, price,  status) VALUES ('Cherry', '21.1', 'DISCOUNT');
INSERT INTO public.products (name, price,  status) VALUES ('Coconut', '11.5', 'DISCOUNT');
INSERT INTO public.products (name, price,  status) VALUES ('Apple', '13.2', 'DISCOUNT');
INSERT INTO public.products (name, price,  status) VALUES ('Orange', '15.3', 'DISCOUNT');
INSERT INTO public.products (name, price,  status) VALUES ('Sky', '19.2', 'DISCOUNT');

INSERT INTO public.discount_cards (percent_discount) VALUES ('0.05');
INSERT INTO public.discount_cards (percent_discount) VALUES ('0.01');
INSERT INTO public.discount_cards (percent_discount) VALUES ('0.05');
INSERT INTO public.discount_cards (percent_discount) VALUES ('0.02');
INSERT INTO public.discount_cards (percent_discount) VALUES ('0.01');

INSERT INTO public.baskets (discount_card_id, price) VALUES ('1', '15.1');

INSERT INTO public.basket_items (basket_id, product_id, count) VALUES ('1', '3', '1');
INSERT INTO public.basket_items (basket_id, product_id, count) VALUES ('1', '5', '1');

