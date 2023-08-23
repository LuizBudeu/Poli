entity gray2bin is
	port (
		gray2, gray1, gray0: in bit;
		bin2, bin1, bin0: out bit
	);
end entity;

architecture arc of gray2bin is
	signal bin0s, bin1s, bin2s: bit;
	begin
		bin2s <= gray2;
		bin1s <= bin2s xor gray1;
		bin0s <= bin1s xor gray0;
		
		bin2 <= bin2s;
		bin1 <= bin1s;
		bin0 <= bin0s;
	end arc;