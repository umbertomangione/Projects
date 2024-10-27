library ieee;
use ieee.std_logic_1164.all;

Entity CU is
Port(	stato: 	in std_logic_vector(1 downto 0);
	en,cnt: 	in std_logic;
	WA, WB, Exe, Ready: out std_logic);
End CU;
Architecture beh of CU is 
 begin
   WA <= '1' when stato = "01" else '0';
   WB <= '1' when (stato = "01" and en='1') or stato = "10" else '0';
   exe <= '1' when stato = "11" and cnt='1'  else '0';
   Ready <= '1' when stato = "00" else '0';

end beh;

