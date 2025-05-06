create materialized view housings_by_host as
select h.id        as host_id,
       count(ho.id) as num_housings
from housing ho
         left join
     host h on h.id = ho.host_id
group by h.id;

create materialized view hosts_per_country as
select co.id        as county_id,
       count(h.id) as num_hosts
from country co
         left join
     host h on co.id = h.country_id
group by co.id;