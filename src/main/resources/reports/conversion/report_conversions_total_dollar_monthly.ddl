drop table if exists report_conversions_total_dollar_monthly;
create table report_conversions_total_dollar_monthly (tenant_record_id int(11), day date, term varchar(50), count int(10));
