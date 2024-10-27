library ieee;
use ieee.std_logic_1164.all;
use ieee.std_logic_signed.all;


Entity DataPath is
Port(	Din: in std_logic_vector(15 downto 0);
	Clk,WA, WB,WC,WR: in std_logic;
	Ris: out std_logic_vector(15 downto 0)
);
End DataPath;
Architecture beh of DataPath is 
signal A, B, ALUris  : std_logic_vector(15 downto 0);
signal C : std_logic_vector(1 downto 0);

begin		   
	
   process(clk)	
   

   begin 
	   if clk'event and clk='0' then
	   	 if WA = '1' then A <= Din;
			end if;
		 if WB = '1' then B <= Din;
		 end if;	
		 if WC = '1' then C <= Din(1 downto 0);
		 end if;
		 if WR = '1' then case C is
		  					when "00" => Ris <= A + B;
		  					when "01" => Ris <= A - B;
		  					when "10" => Ris <= A OR B;
		  					when others => Ris <= A and B;
						end case;
		 end if;	  
	   end if;
	   
	   
   end process;
   
	
end beh;