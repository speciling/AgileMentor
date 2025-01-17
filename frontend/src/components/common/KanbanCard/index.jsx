import React from 'react';
import PropTypes from 'prop-types';
import { Box, Paper, Typography, IconButton } from '@mui/material';
import ArrowForwardRoundedIcon from '@mui/icons-material/ArrowForwardRounded';
import ArrowBackRoundedIcon from '@mui/icons-material/ArrowBackRounded';

const KanbanCard = ({ title, status, titleFontSize, onChangeStatus }) => {
  const handleStatusChange = (direction) => {
    if (direction === 'forward') {
      if (status === 'todo') {
        onChangeStatus('inprogress');
      } else if (status === 'inprogress') {
        onChangeStatus('done');
      }
    } else if (direction === 'backward') {
      if (status === 'inprogress') {
        onChangeStatus('todo');
      } else if (status === 'done') {
        onChangeStatus('inprogress');
      }
    }
  };

  return (
    <Paper
      sx={{
        display: 'flex',
        alignItems: 'center',
        justifyContent: 'space-between',
        padding: 2,
        borderRadius: 2,
        backgroundColor: '#fff7f7',
        marginBottom: 1,
      }}
      elevation={2}
    >
      <Box>
        <Typography variant="subtitle2" sx={{ fontSize: titleFontSize }}>
          {title}
        </Typography>
      </Box>
      <Box sx={{ display: 'flex', gap: 1 }}>
        {(status === 'inprogress' || status === 'done') && (
          <IconButton
            size="small"
            sx={{ backgroundColor: '#f8bbd0' }}
            onClick={() => handleStatusChange('backward')}
          >
            <ArrowBackRoundedIcon sx={{ color: '#ffffff' }} fontSize="small" />
          </IconButton>
        )}
        {(status === 'todo' || status === 'inprogress') && (
          <IconButton
            size="small"
            sx={{ backgroundColor: '#f8bbd0' }}
            onClick={() => handleStatusChange('forward')}
          >
            <ArrowForwardRoundedIcon sx={{ color: '#ffffff' }} fontSize="small" />
          </IconButton>
        )}
      </Box>
    </Paper>
  );
};

KanbanCard.propTypes = {
  title: PropTypes.string.isRequired,
  status: PropTypes.string.isRequired,
  titleFontSize: PropTypes.string,
  onChangeStatus: PropTypes.func.isRequired,
};

KanbanCard.defaultProps = {
  titleFontSize: '1.1rem',
};

export default KanbanCard;
