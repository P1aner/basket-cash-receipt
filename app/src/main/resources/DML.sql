INSERT INTO public.products (id, name, price,  status) VALUES ('1', 'Cherry', '21.1', 'DISCOUNT');
INSERT INTO public.products (id, name, price,  status) VALUES ('2', 'Coconut', '11.5', 'DISCOUNT');
INSERT INTO public.products (id, name, price,  status) VALUES ('3', 'Apple', '13.2', 'DISCOUNT');
INSERT INTO public.products (id, name, price,  status) VALUES ('4', 'Orange', '15.3', 'DISCOUNT');
INSERT INTO public.products (id, name, price,  status) VALUES ('5', 'Sky', '19.2', 'DISCOUNT');

INSERT INTO public.discount_cards (id) VALUES ('1');
INSERT INTO public.discount_cards (id) VALUES ('2');
INSERT INTO public.discount_cards (id) VALUES ('3');
INSERT INTO public.discount_cards (id) VALUES ('4');
INSERT INTO public.discount_cards (id) VALUES ('5');

INSERT INTO public.baskets (id, discount_card_id, price) VALUES ('1', '1', '15.1');

INSERT INTO public.basket_items (basket_id, product_id, count) VALUES ('1', '3', '1');
INSERT INTO public.basket_items (basket_id, product_id, count) VALUES ('1', '5', '1');

