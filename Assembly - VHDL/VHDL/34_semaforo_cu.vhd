library ieee;
use ieee.std_logic_1164.all;

entity semaforo_cu is
port ( start, clk, cond: in std_logic;
	Wconta, SelSoglia: out std_logic;
	Red, Yellow, Green: out std_logic);
end semaforo_cu;

architecture beh of semaforo_cu is
type stato is (rosso,gialloverde,verde,giallorosso);
signal st: stato;
begin

-- fsm
 process(clk)
 begin
   if clk='0' and clk'event then
     case st is
      when rosso =>  if start = '1' then st <= gialloverde;
					else st <= rosso;
			end if;
	when gialloverde => if cond='0' then st <= verde;
				else st <= gialloverde;
				end if;
	when verde => 	if cond='0' then st <= giallorosso;
			else st <= verde;
			end if;
	when giallorosso => if cond ='0' then st <= rosso;
				else st <= giallorosso;
				end if;
     end case;
   end if;
end process;

-- generazione dei segnali di controllo
  Wconta <= '0' when st = rosso else '1';
  SelSoglia <= '1' when st = verde else '0';

  Red <= '1' when st= rosso else '0';
  Yellow <= '1' when st=gialloverde or st = giallorosso else '0';
  Green <= '1' when st = verde else '0';

end beh;

