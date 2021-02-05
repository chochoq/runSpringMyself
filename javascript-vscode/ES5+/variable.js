// use strict
'use strict';

//variable rw(read/write)
// let (add in ES6)
let globalName = 'global name'
{
let name = 'cho';
console.log(name);
name='hello';
console.log(name);
}
console.log(name);
console.log(globalName);

// var(don't ever use this)
// var hoisting 

// Constants r(read only )
const daysInWeek = 7;
const maxNumber = 5;

// variable types
// primitive(single item): Number, String, Bollean, Null, Undefiedn,symbol
// object(box container) 레퍼런스가 메모리가 저장
// function(first-class function)

const count = 17;
const size = 12.2;
console.log(`value:${count},type:${typeof count}`);
console.log(`value:${size},type:${typeof size}`);

//number - speicla numeric values : infinity, -infinity,NaN
const infinity = 1/0;
const nagativeInfinity = -1/0;
const nAn = 'not a number' /2;

console.log(infinity);
console.log(nagativeInfinity);
console.log(nAn);

// bigInt(fairly new, don't use it yet)
const bigInt = 3423423422223444388883n
console.log(`value:${bigInt}, type: ${typeof bigInt}`);

//String
const char = 'c';
const brendan = 'brendan';
const greeting = 'hello' + brendan;
console.log(`value:${greeting},type:${typeof greeting}`);
const helloBob = `hi ${brendan}!`; 
console.log(`value:${helloBob}, type:${typeof helloBob}`);

//boolean
//false : 0, null, undefined, Nan.''
//true: false 아닌 다른 벨류값들

//symbol 고유 식 별자

//object