import React from 'react';
import { Box, Paper, Typography } from '@mui/material';
// eslint-disable-next-line import/no-extraneous-dependencies
import { LineChart } from '@mui/x-charts';
import mockSprints from '../../../mocks/mockSprints';

const BurndownChart = () => {
  const totalTasks = 50;
  const pData = mockSprints.map((sprint) => sprint.completed_tasks_count);
  const xLabels = mockSprints.map((sprint) => sprint.end_date);

  const remainingData = [];
  let cumulativeCompleted = 0;
  pData.forEach((completed) => {
    cumulativeCompleted += completed;
    remainingData.push(totalTasks - cumulativeCompleted);
  });

  return (
    <Box
      sx={{
        height: '65vh',
        width: '71vw',
        display: 'flex',
        justifyContent: 'center',
        alignItems: 'center',
      }}
    >
      <Paper
        sx={{
          height: '100%',
          width: '100%',
          borderRadius: 3,
          padding: 0,
          display: 'flex',
          flexDirection: 'column',
          justifyContent: 'center',
          alignItems: 'center',
        }}
        elevation={3}
      >
        <Typography variant="h6" sx={{ fontSize: '35px', marginTop: '10px' }}>
          Burndown Chart
        </Typography>
        <Box
          sx={{
            height: '90%',
            width: '90%',
            display: 'flex',
            justifyContent: 'center',
            alignItems: 'center',
          }}
        >
          <LineChart
            width={1000}
            height={450}
            series={[
              { data: pData, label: 'Completed Tasks' },
              { data: remainingData, label: 'Remaining Tasks' },
            ]}
            xAxis={[{ scaleType: 'point', data: xLabels }]}
            yAxis={[{ min: 0 }]}
            slotProps={{
              legend: {
                itemGap: 20,
              },
            }}
          />
        </Box>
      </Paper>
    </Box>
  );
};

export default BurndownChart;
