#include <iostream>
#include <cmath>
#include <set>

int main() {
    uint32_t l, r;
    std::cin >> l >> r;

    std::set<std::pair<uint32_t, uint32_t>> divider_quantity;

    for (uint32_t i = l; i <= r; ++i) {
        uint32_t count = 0;
        double sqrt = std::sqrt(i);

        for (uint32_t j = 1; j <= sqrt; ++j) {
            if (i % j == 0) {
                if (i / j == j) {
                    ++count;
                } else {
                    count += 2;
                }
            }
        }

        divider_quantity.emplace(count, i);
    }

    for (const auto& [_, n] : divider_quantity) {
        std::cout << n << ' ';
    }
    return 0;
}
