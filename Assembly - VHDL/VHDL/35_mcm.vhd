library ieee;
use ieee.std_logic_1164.all;

entity mcm is
port(clk,start: in std_logic;
	A,B : in std_logic_vector(7 downto 0);
ris: out std_logic_vector(7 downto 0);
Ready: out std_logic
);
end mcm;

architecture sch of mcm is
component mcm_datapath is
port ( clk: in std_logic;
	A,B : in std_logic_vector(7 downto 0);
	selA,SelB, WA,WB,WR: in std_logic;
	diversi,minore: out std_logic;
	ris: out std_logic_vector(7 downto 0)
);
end component;
component mcm_cu is
port ( clk, start, diversi, minore: in std_logic;
	selA,selB, WA,WB,WR,Ready: out std_logic);

end component;

signal selA,SelB, WA,WB,WR:  std_logic;
signal diversi,minore:  std_logic;

begin

dp: mcm_datapath port map (clk,A,B,selA,SelB, WA,WB,WR,diversi,minore,ris);

cu: mcm_cu port map (clk, start, diversi, minore, selA,selB, WA,WB,WR,Ready);

end sch;

