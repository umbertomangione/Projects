library ieee;
use ieee.std_logic_1164.all;

entity semaforo is
port (start, clk: in std_logic;
Red, Yellow, Green: out std_logic);
end semaforo;

architecture sch of semaforo is
component semaforo_datapath is
port( clk, selSoglia, Wconta: in std_logic;
	cond: out std_logic);
end component;
 component semaforo_cu is
port ( start, clk, cond: in std_logic;
	Wconta, SelSoglia: out std_logic;
	Red, Yellow, Green: out std_logic);
end component;
signal Wconta, cond, SelSoglia: std_logic;
begin
 dp: semaforo_datapath port map (clk, selSoglia, Wconta,cond);
 cu: semaforo_cu port map (start, clk, cond, Wconta, SelSoglia, Red, Yellow, Green);

end sch;