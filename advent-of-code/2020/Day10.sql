create table lines (
  number int not null
);

\copy lines (number) from program 'cat input.txt';

drop table if exists chain;
create table chain (
  id serial not null,
  number int not null
);

insert into chain (number) values (0);

insert into chain (number)
select number
from lines
order by number;

insert into chain (number)
select max(number) + 3
from lines;

drop table lines;

select exp(sum(ln(multiplier))) as first
from (
  select count(*) as multiplier
  from chain First, chain Second
  where First.number + 1 = Second.number

  union

  select count(*) as multiplier
  from chain First, chain Second
  where First.number + 3 = Second.number
    and not exists (
      select First.number
      from chain Third
      where First.number + 1 = Third.number
    )
) ONES_AND_THREES;

drop table if exists ways;
create table ways (
  id serial not null,
  number bigint not null
);

insert into ways (number) select 0 from chain;
update ways set number = 1 where id = 1;

create or replace function CountWays() returns void
as
$$
declare t_row ways%rowtype;
begin
  FOR t_row in select * from chain order by id
  loop
    update ways
    set number = (
      select sum(number)
      from ways
      where id in (
        select Previous.id
        from chain Previous, (
          select number
          from chain
          where chain.id = t_row.id
        ) Fixed
        where Fixed.number - Previous.number <= 3
      )
    )
    where id = t_row.id;
  end loop;
end;
$$
language plpgsql;

select CountWays();
select max(number) as second from ways;
