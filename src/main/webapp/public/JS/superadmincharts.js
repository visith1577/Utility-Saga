document.addEventListener('DOMContentLoaded', function () {
    var data = {
        labels: ['Western', 'Eastern', 'Northern', 'Southern', 'Central'],
        datasets: [{
            data: [20, 15, 10, 8, 12],
            backgroundColor: ['#023e8a', '#36A2EB', '#FFCE56', '#4BC0C0', '#9966FF']
        }]
    };
  
    var ctx = document.getElementById('regionalAdminChart').getContext('2d');
    ctx.width = 50; 
    ctx.height = 50;
    var myChart = new Chart(ctx, {
        type: 'doughnut',
        data: data
    });
  });
  
  document.addEventListener('DOMContentLoaded', function () {
      var data = {
          labels: ['Western', 'Eastern', 'Northern', 'Southern', 'Central'],
          datasets: [{
              label: 'New Connections',
              data: [30, 25, 15, 10, 20],
              backgroundColor: ['#023e8a', '#36A2EB', '#FFCE56', '#4BC0C0', '#9966FF']
          }]
      };
    
      var ctx = document.getElementById('newConnectionsChart');
      ctx.width = 50;
      ctx.height = 50; 
      var myChart = new Chart(ctx, {
          type: 'bar',
          data: data,
          options: {
              scales: {
                  y: {
                      beginAtZero: true
                  }
              }
          }
      });
    });
    
  
    document.addEventListener('DOMContentLoaded', function () {
      var waterConsumptionData = {
          labels: ['Jan', 'Feb', 'Mar', 'Apr', 'May'],
          datasets: [
              {
                  label: 'Western Region',
                  data: [50, 45, 60, 55, 70],
                  borderColor: '#023e8a',
                  borderWidth: 2,
                  fill: false
              },
              {
                  label: 'Eastern Region',
                  data: [40, 55, 50, 65, 45],
                  borderColor: '#36A2EB',
                  borderWidth: 2,
                  fill: false
              },
              {
                  label: 'Northern Region',
                  data: [70, 56, 30, 25, 10],
                  borderColor: '#FFCE56',
                  borderWidth: 2,
                  fill: false
              },
              {
                  label: 'Southern Region',
                  data: [60, 20, 75, 25, 95],
                  borderColor: '#4BC0C0',
                  borderWidth: 2,
                  fill: false
              },
              {
                  label: 'Central Region',
                  data: [80, 25, 26, 45, 85],
                  borderColor: '#9966FF',
                  borderWidth: 2,
                  fill: false
              },
          ]
      };
  
      var waterConsumptionCanvas = document.getElementById('waterConsumptionChart');
      waterConsumptionCanvas.height = 50;
      waterConsumptionCanvas.width = 50;
  
      var waterConsumptionChart = new Chart(waterConsumptionCanvas.getContext('2d'), {
          type: 'line',
          data: waterConsumptionData,
          options: {
              scales: {
                  x: {
                      type: 'category',
                      labels: waterConsumptionData.labels,
                      beginAtZero: true
                  },
                  y: {
                      beginAtZero: true
                  }
              }
          }
      });
  });
  