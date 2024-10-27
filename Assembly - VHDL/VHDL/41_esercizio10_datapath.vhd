library ieee;
use ieee.std_logic_1164.all;
use ieee.std_logic_unsigned.all;

Entity Datapath is
Port(	D: in std_logic_vector(15 downto 0);
	clk,WA,WS,Wp: in std_logic;
	RIS: out std_logic_vector(15 downto 0)
);
End DataPath;

Architecture beh of Datapath is 
signal RegA,RegS: std_logic_vector(15 downto 0);
 begin
  process(clk)
	variable r: std_logic_vector(31 downto 0);
   begin
      if clk'event and clk='0' then
        -- RegA
	if WA = '1' then
		RegA <= D; 
	end if;
	-- RegS
	if WS = '1' then
		RegS <= RegA + D; 
	end if;
	-- Ris
	if Wp='1' then
		r:= RegS * D;
		Ris <= r(15 downto 0);
	end if;
     end if;

end process;
end beh;

