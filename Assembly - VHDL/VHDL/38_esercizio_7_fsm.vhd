library ieee;
use ieee.std_logic_1164.all;

entity fsm_es7 is
port ( OK, clk, imp, cond: in std_logic;
	fine: out std_logic);
end fsm_es7;
architecture beh of fsm_es7 is

type stato is (init, imp0, imp1, incremento);
signal stati : stato;

begin
  process(clk)
   begin
	if clk='0' and clk'event then
 		case stati is
		 when init => if OK = '1' then stati <= imp1;
				else stati <= init;
				end if;
		  when imp0 => if imp = '0' then
			             if cond = '0' then stati <= imp1;
					else stati <= init;
				     end if;
				else stati <= imp0;
				end if;
		 when imp1 => if imp='1' then stati <= incremento;
				else stati <= imp1;
				end if;
		when others => stati <= imp0;

		end case;
	end if;
 end process;

 fine <= '1' when stati = init else '0';

end beh;
