// 1. String concatenation
console.log('my' + ' cat');
console.log('1' + 2);
console.log(`string literals: 1 + 2 = ${1 + 2}`);

// 2. numberic operators
console.log(1 + 1); //add
console.log(1 - 1); // substract
console.log(1 / 1); //divide
console.log(1 * 1); //multiply
console.log(5 % 2); //remainder
console.log(2 ** 3);    //exponentiation

// 3. Increment and Decrement operators
let counter = 2;
const preIncrement = ++counter;
console.log(`preIncrement: ${preIncrement}, couter: ${counter}`)

const postIncrement = counter++;
console.log(`postIncrement: ${postIncrement}, couter: ${counter}`)
//--역시 같음

// 4.Assignment(할당) operators 
let x = 3;
let y = 6;
x += y; //x = x+y
x -= y;
x *= y;
x /= y;

// 5.Comparison(비교) operators
console.log(10 < 6); // less than
console.log(10 <= 6); //less than or equal
console.log(10 > 6); //greater than
console.log(10 >= 6); //greater than or equal


// 6. Logical operators : || or, && and , ! not
const value1 = false;
const value2 = 4 < 2;

// || or ,finds then first trurhy value
console.log(`or: ${value1 || value2 || check()}`);
// && and , finds then first falsy value
console.log(`and: ${value1 && value2 && check()}`);

function check() {
    for (let i = 0; i < 10; i++) {
        console.log('nooo');
    }
    return true;
}

// ! not
console.log(!value1);

// 7. Equality
const stringFive = '5';
const numberFive = 5;

// == loose equality, with type conversion
// 타입이 달라도 안에 있는 내용이 같으면 true로 반환됨
console.log(stringFive == numberFive);
console.log(stringFive != numberFive);

// === strict equality, no type conversion
// 타입이 다르면 내용이 같더라도 false (사용권장)
console.log(stringFive === numberFive);
console.log(stringFive !== numberFive);

// object equality by refernce
const a = { name: 'cho' };
const b = { name: 'cho' };
const c = a;
console.log(a == b);
console.log(a === b);
console.log(a === c);

// equality - puzzler 생각해보기 아직모르겟다
console.log(0 == false); 
console.log(0 === false);
console.log('' == false);
console.log('' === false);
console.log(null == undefined);
console.log(null === undefined);

// 8. if ,else


// 9. Ternary operator : ?
// condition ? value1 : value2?;
console.log( name === 'cho' ? 'yes' :'no');

// 10. switch
// 11. loops 
// while
// do while    ????이해못함

// for loop

// nested loops