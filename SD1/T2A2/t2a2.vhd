entity gray2bin is
	generic(
		size: natural := 3
	);
	port (
		gray: in bit_vector(size-1 downto 0);
		bin: out bit_vector(size-1 downto 0)
	);
end entity;

architecture arc of gray2bin is
	signal bins: bit_vector(size-1 downto 0);
	begin
		bins(size-1) <= gray(size-1);
		bin(size-1) <= bins(size-1);
		laco: for i in size-2 downto 0 generate
			bins(i) <= gray(i) xor bins(i+1);
			bin(i) <= bins(i);
			end generate laco;
end arc;