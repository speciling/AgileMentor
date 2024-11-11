import React from 'react';
import PropTypes from 'prop-types';
import { Box, Typography, Divider } from '@mui/material';
import CircleIcon from '@mui/icons-material/Circle';
import mockBacklogs from '../../../mocks/mockBacklogs';

const OngoingTasksList = ({ memberId }) => {
  const filteredBacklogs = mockBacklogs.filter(
    (backlog) => backlog.member_id === memberId && backlog.status === 'inprogress'
  );

  const projects = [...new Set(filteredBacklogs.map((task) => task.project_id))];

  return (
    <Box>
      {projects.map((projectId, index) => (
        <Box key={projectId} mb={1.5}>
          {index > 0 && <Divider sx={{ mb: 1 }} />}
          {filteredBacklogs
            .filter((task) => task.project_id === projectId)
            .map((task) => (
              <Box
                key={task.backlog_id}
                display="flex"
                alignItems="center"
                justifyContent="space-between"
                mb={1}
              >
                <Box display="flex" alignItems="center">
                  <CircleIcon sx={{ color: '#0eaaf9', fontSize: '0.5rem', mr: 1 }} />
                  <Typography variant="body1" sx={{ fontSize: '1rem', color: '#333' }}>
                    {task.title}
                  </Typography>
                </Box>
                <Typography variant="body2" sx={{ color: '#666', fontSize: '0.8rem' }}>
                  {projectId}
                </Typography>
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
