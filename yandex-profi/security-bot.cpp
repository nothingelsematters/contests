#include <iostream>
#include <algorithm>

int main() {
    uint32_t x, y, a, b, c, d;
    std::cin >> x >> y >> a >> b >> c >> d;
    std::cout << std::min({a, b, x - c, y - d});
}
