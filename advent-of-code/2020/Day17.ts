function step(state: boolean, actives: number): boolean {
    return !state && actives === 3 || state && (actives === 3 || actives === 4);
}

function maskAt(n: number, base: number, position: number): number {
    return Math.floor(n / Math.pow(base, position)) % base;
}

function maskTo(n: number, base: number, position: number): number {
    return n * Math.pow(base, position);
}

function conwoyCubes(iterations: number, dimensions: number, initialGrid: boolean[][]): number {
    const gridSize = initialGrid.length + 2 * (iterations + 1);
    const margin = iterations + 1;
    const defaultValue = false;

    let grid: [boolean] = [defaultValue];
    for (let mask = 0; mask < Math.pow(gridSize, dimensions) - 1; mask++) {
        grid.push(defaultValue);
    }

    let indexMargin = 0;
    for (let i = 0; i < dimensions - 2; i++) {
        indexMargin += maskTo(Math.floor(gridSize / 2), gridSize, i);
    }

    for (let i = 0; i < initialGrid.length; i++) {
        for (let j = 0; j < initialGrid[i].length; j++) {
            let index = indexMargin + maskTo(margin + i, gridSize, dimensions - 2) +
                maskTo(margin + j, gridSize, dimensions - 1);
            grid[index] = initialGrid[i][j];
        }
    }

    for (let i = 0; i < iterations; i++) {
        let clone: [boolean] = [...grid];

        for (let j = 0; j < grid.length; j++) {
            let indices = [];
            for (let k = 0; k < dimensions; k++) {
                indices.push(maskAt(j, gridSize, k));
            }

            if (indices.some(x => x === 0 || x === gridSize - 1)) {
                continue;
            }

            let actives = 0;
            for (var k = 0; k < Math.pow(3, dimensions); k++) {
                let index = j;
                for (let h = 0; h < dimensions; h++) {
                    index += maskTo(maskAt(k, 3, h) - 1, gridSize, h);
                }

                if (grid[index]) {
                    actives++;
                }
            }
            clone[j] = step(grid[j], actives);
        }

        grid = clone;
    }

    return grid.filter(x => x).length;
}

let input = document.getElementsByTagName('pre')[0].textContent.split('\n');
const initialGrid = input.slice(0, input.length - 1).map(x => x.split('').map(y => y === '#'));
const first = conwoyCubes(6, 3, initialGrid);
const second = conwoyCubes(6, 4, initialGrid);
console.log(`${first} ${second}`);
