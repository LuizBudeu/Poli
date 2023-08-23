entity incinerador is
	port (
		S: in bit_vector(2 downto 0);
		P: out bit
	);
	
end entity;

architecture arch of incinerador is
	signal termo1, termo2, termo3: bit;
	begin
		termo1 <= S(0) and s(1);
		termo2 <= S(1) and s(2);
		termo3 <= S(2) and S(0);
		
		P <= termo1 or termo2 or termo3;
		
end architecture;