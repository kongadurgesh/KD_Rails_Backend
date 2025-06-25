begin
   execute immediate 'CREATE SEQUENCE route_sequence START WITH 101;';
exception
   when others then
      if sqlcode != -955 then
         raise;
      end if;
end;
/