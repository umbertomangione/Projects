library ieee;
use ieee.std_logic_1164.all;


entity tb_es5 is
end tb_es5;

architecture tbb_es5 of tb_es5 is
component riconosci_sequenza is
port ( clk: in std_logic;
	xy: in std_logic_vector( 1 downto 0);
	z: out std_logic);
end component;

signal clk,z: std_logic;
signal xy: std_logic_vector( 1 downto 0);

begin

dut: riconosci_sequenza port map (clk, xy,z);

process
begin
 clk<= '0';
wait for 5 ns;
 clk<= '1';
wait for 5 ns;
end process;
 
xy <= 	"00" after 0 ns, "01" after 11 ns, "11" after 21 ns,
      	"10" after 31 ns, "01" after 51 ns,
	"10" after 61 ns, "11" after 71 ns, "10" after 81 ns, 
	"11" after 91 ns, "10" after 101 ns, "00" after 111 ns;


end tbb_es5;
