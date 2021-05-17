
grammar Comm;
r: 'draw' SHAPE | 'draw' WS SHAPE;
SHAPE: 'circle' | 'square';
WS: [\t\r\n]+ -> skip; 