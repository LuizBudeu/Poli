library ieee;
use ieee.numeric_bit.all;
use ieee.math_real.all;

entity secded_dec16 is
	port (
		mem_data: in bit_vector(21 downto 0);
		u_data: out bit_vector(15 downto 0);
		syndrome: out natural;
		two_errors: out bit;
		one_error: out bit
	);
end entity;

architecture arc of secded_dec16 is	
	signal p0, p1, p2, p3, p4, p5: bit;
	signal p0c, p1c, p2c, p3c, p4c, p5c, p5t: bit;
	signal gen_parity: bit_vector (4 downto 0);
	
	begin
		p0 <= mem_data(0);
		p1 <= mem_data(1);
		p2 <= mem_data(3);
		p3 <= mem_data(7);
		p4 <= mem_data(15);
		p5 <= mem_data(21);
		
		p0c <= mem_data(20) xor mem_data(18) xor mem_data(16) xor mem_data(14) xor mem_data(12) xor mem_data(10) xor mem_data(8) xor mem_data(6) xor mem_data(4) xor mem_data(2);
		p1c <= mem_data(18) xor mem_data(17) xor mem_data(14) xor mem_data(13) xor mem_data(10) xor mem_data(9) xor mem_data(6) xor mem_data(5) xor mem_data(2);
		p2c <= mem_data(20) xor mem_data(19) xor mem_data(14) xor mem_data(13) xor mem_data(12) xor mem_data(11) xor mem_data(6) xor mem_data(5) xor mem_data(4);
		p3c <= mem_data(14) xor mem_data(13) xor mem_data(12) xor mem_data(11) xor mem_data(10) xor mem_data(9) xor mem_data(8);
		p4c <= mem_data(20) xor mem_data(19) xor mem_data(18) xor mem_data(17) xor mem_data(16);
		p5c <= mem_data(20) xor mem_data(19) xor mem_data(18) xor mem_data(17) xor mem_data(16) xor mem_data(15) xor mem_data(14) xor mem_data(13) xor mem_data(12) xor mem_data(11) xor mem_data(10) xor mem_data(9) xor mem_data(8) xor mem_data(7) xor mem_data(6) xor mem_data(5) xor mem_data(4) xor mem_data(3) xor mem_data(2) xor mem_data(1) xor mem_data(0);
		
		gen_parity(0) <= p0 xor p0c;
		gen_parity(1) <= p1 xor p1c;
		gen_parity(2) <= p2 xor p2c;
		gen_parity(3) <= p3 xor p3c;
		gen_parity(4) <= p4 xor p4c;
		p5t <= p5 xor p5c;
		
		syndrome <= to_integer(unsigned(gen_parity));
		
		two_errors <= '1' when p5t = '0' and gen_parity /= "00000" else '0';
		one_error <= '1' when p5t = '1' else '0';
		
		u_data(0) <= mem_data(2) when gen_parity /= "00011" else not mem_data(2);
		u_data(1) <= mem_data(4) when gen_parity /= "00101" else not mem_data(4);
		u_data(2) <= mem_data(5) when gen_parity /= "00110" else not mem_data(5);
		u_data(3) <= mem_data(6) when gen_parity /= "00111" else not mem_data(6);
		u_data(4) <= mem_data(8) when gen_parity /= "01001" else not mem_data(8);
		u_data(5) <= mem_data(9) when gen_parity /= "01010" else not mem_data(9);
		u_data(6) <= mem_data(10) when gen_parity /= "01011" else not mem_data(10);
		u_data(7) <= mem_data(11) when gen_parity /= "01100" else not mem_data(11);
		u_data(8) <= mem_data(12) when gen_parity /= "01101" else not mem_data(12);
		u_data(9) <= mem_data(13) when gen_parity /= "01110" else not mem_data(13);
		u_data(10) <= mem_data(14) when gen_parity /= "01111" else not mem_data(14);
		u_data(11) <= mem_data(16) when gen_parity /= "10001" else not mem_data(16);
		u_data(12) <= mem_data(17) when gen_parity /= "10010" else not mem_data(17);
		u_data(13) <= mem_data(18) when gen_parity /= "10011" else not mem_data(18);
		u_data(14) <= mem_data(19) when gen_parity /= "10100" else not mem_data(19);
		u_data(15) <= mem_data(20) when gen_parity /= "10101" else not mem_data(20);
		
end architecture;