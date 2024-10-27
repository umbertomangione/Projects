library ieee;
use ieee.std_logic_1164.all;

entity mcm_cu is
port ( clk, start, diversi, minore: in std_logic;
	selA,selB, WA,WB,WR,Ready: out std_logic);

end mcm_cu;

architecture beh of mcm_cu is
type stato is (idle, init, compare, agg_ma, agg_mb, agg_ris);
signal st : stato;

begin

process(clk)
begin
 if clk'event and clk='0' then
    case st is
	when idle => if start = '1' then st <= init;
			else st <= idle;
			end if;
	when init => st <= compare;
	when compare => if diversi = '1' then
				if minore = '0' then st <= agg_mb;
				else st <= agg_ma;
				end if;
			else st<= agg_ris;
			end if;
	when agg_ma | agg_mb => st <= compare;

	when agg_ris => st <= idle;
end case;
end if;
end process;

selA <= '0' when st = idle or st = init else '1';
selB <= '0' when st = idle or st = init else '1';


WA <= '1' when st = init or st = agg_ma else '0';

WB <= '1' when st = init or st = agg_mb else '0';
WR <= '1' when st = agg_ris else '0';

Ready <= '1' when st = idle else '0';

end beh;