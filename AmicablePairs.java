import java.util.Map;
import java.util.function.Function;
import java.util.stream.LongStream;
import java.util.stream.Collectors;

public class AmicablePairs {
	public static void main(final String... args) {
		int limit = 20_000;

		Map<Long, Long> map = LongStream.rangeClosed(1, limit)
				.parallel()
				.boxed()
				.collect(Collectors.toMap(Function.identity(), AmicablePairs::properDivsSum));

		LongStream.rangeClosed(1, limit)
				.forEach( n -> {
					long m = map.get(n);
					if (m > n && m <= limit && n == map.get(m))
						System.out.printf("%s %s %n", n, m);
				});
	}

	public static Long properDivsSum(long n) {
		return LongStream.rangeClosed(1, (n + 1) / 2)
			.filter(i -> n % i == 0).sum();
	}
} 
