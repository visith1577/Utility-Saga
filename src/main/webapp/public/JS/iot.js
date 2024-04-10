const iotform= document.getElementById('iotform');
const iottable= document.getElementById('iottable');

iotform.addEventListener('submit', function (event){
    event.preventDefault();

    const serialNo= document.getElementById('serialNo').value;
    const name= document.getElementById('name').value;
    const pConsumption= document.getElementById('pConsumption').value;

    const newRow= document.createElement('tr');

    const serialcell= document.createElement('td');
    serialcell.innerText= serialNo;
    const nameCell= document.createElement('td');
    nameCell.innerText= name;
    const pConCell= document.createElement('td');
    pConCell.innerText= pConsumption;

    const statusLabel = document.createElement('label');
    statusLabel.classList.add('switch');
    const statusToggle = document.createElement('input');
    statusToggle.type = 'checkbox';
    statusToggle.id = 'statusToggle';
    const sliderSpan = document.createElement('span');
    sliderSpan.classList.add('slider', 'round');

    statusLabel.appendChild(statusToggle);
    statusLabel.appendChild(sliderSpan);


    newRow.appendChild(serialcell);
    newRow.appendChild(nameCell);
    newRow.appendChild(pConCell);
    newRow.appendChild(statusLabel);

    iottable.getElementsByTagName('tbody')[0].appendChild(newRow);

    iotform.reset();
});