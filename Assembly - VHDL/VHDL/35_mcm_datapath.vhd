library ieee;
use ieee.std_logic_1164.all;
use ieee.std_logic_unsigned.all;

entity mcm_datapath is
port ( clk: in std_logic;
	A,B : in std_logic_vector(7 downto 0);
	selA,SelB, WA,WB,WR: in std_logic;
	diversi,minore: out std_logic;
	ris: out std_logic_vector(7 downto 0)
);
end mcm_datapath;

architecture beh of mcm_datapath is
signal ma,mb: std_logic_vector(7 downto 0);
begin

 diversi <= '1' when ma /= mb else '0';
 minore <= '1' when ma < mb else '0';

  process(clk)
begin
 if clk='0' and clk'event then
     if WA ='1' then
           if selA = '0' then  ma <= A;
		else  ma <= ma + A;
	   end if;
     end if;
	if WB = '1' then
	   if selB ='0' then mb<= B;
		else mb <= mb+ B;
		end if;
	end if;
	if WR ='1' then  Ris <= ma;
	end if;

 end if;
end process;

end beh;
