library ieee;
use ieee.std_logic_1164.all;

Entity DPath is
Port(	Din: in std_logic_vector(15 downto 0);
	OP: in std_logic_vector(1 downto 0);
	Clk,WA, WR: in std_logic;
	Ris: out std_logic_vector(15 downto 0)
);
End DPath;
Architecture beh of DPath is 
signal RegA: std_logic_vector(15 downto 0); 
begin
   process(clk)
     begin
	if clk'event and clk='0' then
		if WA='1' then RegA <= Din;
		end if;
		if WR ='1' then
			case OP is
			 when "00" => Ris <= RegA AND Din;
			 when "01" => Ris <= RegA OR Din;
			 when "10" => Ris <= RegA - Din;
			 when others => Ris <= RegA + Din;

		        end case;		
		end if;
	end if;

     end process;	
  
end beh;

