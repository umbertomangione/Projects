library ieee;
use ieee.std_logic_1164.all;

entity CU_esercizio6 is
port (clk,start: in std_logic;
      OP: in std_logic_vector (1 downto 0);
      conta: in integer range 0 to 1;
      Ready, WOP, WA, WB, En_1c, En_2c: out std_logic);
end CU_esercizio6;

architecture beh of CU_esercizio6 is
type stato is (idle, ReadOP, ReadA, ReadB, Exe_1c, Exe_2c);
signal st: stato;

begin

process(clk)
begin
   if clk'event and clk='0' then
      case st is
          when idle => if start ='1' then st <= ReadOP;
                       else st <= idle;
                       end if;
          when ReadOP => st <= ReadA;
          when ReadA => st <= ReadB;
          when ReadB => if OP(1) ='0' then st <= Exe_1c;
                     	else st <= Exe_2c; 
                     	end if;
          when Exe_2c => if conta < 1 then st <= Exe_2c;
                            else  st <= idle;
                         end if;
          when others => st <= idle; -- ovvero when exe_1c => st <= idle;
      end case;
   end if;
end process;

WOP <= '1' when st= ReadOP else '0';
WA <= '1' when st= ReadA else '0';
WB <= '1' when st= ReadB else '0';
En_1c <= '1' when st= Exe_1c else '0';
En_2c <= '1' when st= Exe_2c else '0';
Ready <= '1' when st= idle else '0';

end beh;

