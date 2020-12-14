#include <iostream>
#include <cmath>

int main() {
    long double a, b, c, d;
    std::cin >> a >> b >> c >> d;
    size_t first = std::ceil((a / b) * (a / b));
    size_t second = std::floor((c / d) * (c / d));
    std::cout << (first > second ? 0 : second - first + 1) << '\n';
    return 0;
}
