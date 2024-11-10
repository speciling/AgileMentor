import React from 'react';
import PropTypes from 'prop-types';
import { Box, Typography, IconButton, Divider } from '@mui/material';
import InfoIcon from '@mui/icons-material/Info';
import mockBacklogs from '../../../mocks/mockBacklogs';

const OngoingTasksList = ({ memberId }) => {
  const filteredBacklogs = mockBacklogs.filter(
    (backlog) => backlog.member_id === memberId && backlog.status === 'inprogress'
  );

  const projects = [...new Set(filteredBacklogs.map((task) => task.project_id))];

  return (
    <Box>
      {projects.map((projectId, index) => (
        <Box key={projectId} mb={1}>
          {index > 0 && <Divider sx={{ mb: 1 }} />}
          <Typography variant="h6" sx={{ fontWeight: 'bold', fontSize:'1.1rem', color: '#333', mb: 1 }}>
            Project Name: {projectId}
          </Typography>
          {filteredBacklogs
            .filter((task) => task.project_id === projectId)
            .map((task) => (
              <Box
                key={task.backlog_id}
                display="flex"
                alignItems="center"
                justifyContent="space-between"
                mb={0}
              >
                <Typography variant="body1" sx={{ fontSize: '1rem', color: '#333' }}>
                  - {task.title}
                </Typography>
                <IconButton sx={{ color: '#0eaaf9' }} aria-label="info">
                  <InfoIcon fontSize="medium" />
                </IconButton>
              </Box>
            ))}
        </Box>
      ))}
    </Box>
  );
};

OngoingTasksList.propTypes = {
  memberId: PropTypes.number.isRequired,
};

export default OngoingTasksList;
