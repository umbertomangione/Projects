library ieee;
use ieee.std_logic_1164.all;


entity tB_mcm is
end tb_mcm;

architecture tbb_mcm of tb_mcm is

component mcm is
port(clk,start: in std_logic;
	A,B : in std_logic_vector(7 downto 0);
ris: out std_logic_vector(7 downto 0);
Ready: out std_logic
);
end component;

signal start, clk:  std_logic;
signal A,B : std_logic_vector(7 downto 0);
signal ris:  std_logic_vector(7 downto 0);
signal Ready:  std_logic;

begin
 dut: mcm port map (clk,start, A,B,ris,Ready);

   process
begin
 clk<= '0';
wait for 5 ns;
 clk<= '1';
wait for 5 ns;
end process;

 start <= '0' after 0 ns, '1' after 11 ns, '0' after 21 ns,
			  '1' after 211 ns, '0' after 221 ns;  

A <= "00000101" after 0 ns, "00000100" after 120 ns; 
B <= "00000011" after 0 ns, "00000110" after 120 ns;
end tbb_mcm;
