DELETE FROM currencies;
INSERT INTO currencies (id, is_crypto, currency) VALUES (1, 0, 'eur');
INSERT INTO currencies (id, is_crypto, currency) VALUES (2, 1, 'btc');

DELETE FROM rates;
INSERT INTO rates (from_currency_id, rate_timestamp, rate_value, to_currency_id, id) VALUES (1, 1693317634, 24120.01, 2, 1);
INSERT INTO rates (from_currency_id, rate_timestamp, rate_value, to_currency_id, id) VALUES (1, 1693317638, 24119.01, 2, 2);
INSERT INTO rates (from_currency_id, rate_timestamp, rate_value, to_currency_id, id) VALUES (1, 1693317641, 24121.01, 2, 3);

