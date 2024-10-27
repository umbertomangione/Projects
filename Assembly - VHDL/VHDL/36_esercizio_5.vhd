library ieee;
use ieee.std_logic_1164.all;

entity riconosci_sequenza is
port ( clk: in std_logic;
	xy: in std_logic_vector( 1 downto 0);
	z: out std_logic);
end riconosci_sequenza;

architecture beh of riconosci_sequenza is
type stato is (A,B,C,D,E);
signal state: stato;
begin
    process(clk)
  	begin
     if clk'event and clk='0' then
	  case state is 
	   when A => if xy = "10" then state <= B;
			else state <= A;
			end if;
	   when B =>  case xy is
			when "00" | "01" => state <= A;
			when "11" => state <= C;
			when others => state <= B;
		     end case;
	   when C => if xy = "10" then state <= D;
			else state <= A;
			end if;
	   when D => case xy is
			when "00" | "01" => state <= A;
			when "11" => state <= E;
			when others => state <= B;
		     end case;

		when others => if xy(1) = '1' then state <= E;
				else state <= A;
				end if;
end case;
	end if;

end process;

 z<= '1' when state = D and xy = "11" else '0';

end beh;
