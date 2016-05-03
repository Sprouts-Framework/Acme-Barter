DROP PROCEDURE IF EXISTS `acme-barter`.cancelBarters;

DELIMITER //

CREATE PROCEDURE `acme-barter`.cancelBarters()

BEGIN

update `acme-barter`.barter b2 set b2.cancelled = true where b2.id in(
select id from (
	SELECT b.*, m.offerSignedDate, m.requestSignedDate
	FROM `acme-barter`.barter b 
	left JOIN `acme-barter`._match m on (m.offered_id = b.id or m.requested_id = b.id)
	WHERE b.moment <= DATE_ADD(NOW(), INTERVAL -1 MONTH)
) j where j.offerSignedDate is null or j.requestSignedDate is null);

END //

DELIMITER ;