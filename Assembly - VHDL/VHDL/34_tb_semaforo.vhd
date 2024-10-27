library ieee;
use ieee.std_logic_1164.all;


entity tB_sem is
end tb_sem;

architecture tbb_sem of tb_sem is
component semaforo is
port (start, clk: in std_logic;
Red, Yellow, Green: out std_logic);
end component;

signal start, clk:  std_logic;
signal Red, Yellow, Green:  std_logic;

begin
 dut: semaforo port map (start, clk, Red, Yellow, Green);

   process
begin
 clk<= '0';
wait for 5 ns;
 clk<= '1';
wait for 5 ns;
end process;

 start <= '0' after 0 ns, '1' after 11 ns, '0' after 21 ns;  

end tbb_sem;