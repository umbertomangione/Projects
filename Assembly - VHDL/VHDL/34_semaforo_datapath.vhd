library ieee;
use ieee.std_logic_1164.all;

entity semaforo_datapath is
port( clk, selSoglia, Wconta: in std_logic;
	cond: out std_logic);
end semaforo_datapath;

architecture beh of semaforo_datapath is
signal  condt: std_logic;
signal soglia,conta: integer range 0 to 49;

begin
  soglia <= 9 when selSoglia = '0' else 49;
  condt <= '1' when conta < soglia else '0';

  cond <= condt;

  process(clk)
    begin
	if clk'event and clk='0' then
	   if Wconta ='1' then
		if condt = '0' then
			conta<= 0;
		else conta <= conta +1;
		end if;
		-- if conta < soglia then
		-- 	conta<= 0;
		-- else conta<= conta +1;
		-- end if;	
	end if;
	end if;
end process;


end beh;


