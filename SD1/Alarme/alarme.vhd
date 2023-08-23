entity alarme is
	port (
		j0, j1, j2, j3, en0, p: in bit;
		s0: out bit
	);
end entity;

architecture portaslogicas of alarme is
	signal j2n, j3n: bit;
	signal janelas: bit;
begin
	j2n <= not j2;
	j3n <= not j3;
	s0 <= p or (janelas and en0);
	janelas <= j0 or j1 or j2n or j3n;
end architecture;
-- comentario