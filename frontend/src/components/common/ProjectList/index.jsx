import React from 'react';
import { Box, Typography, IconButton, Stack, ButtonBase } from '@mui/material';
import AddRoundedIcon from '@mui/icons-material/AddRounded';
import EditIcon from '@mui/icons-material/Edit';
import DeleteIcon from '@mui/icons-material/Delete';
import mockProjects from '../../../mocks/mockProjects';

const ICON_SIZE = 30;
const FONT_SIZE = '0.8rem';

const ProjectList = () => (
  <Box>
    <Box display="flex" alignItems="center" mb={1}>
      <IconButton
        sx={{
          width: ICON_SIZE,
          height: ICON_SIZE,
          backgroundColor: '#fff',
          borderRadius: '8px',
          border: '1px solid #ffc107',
          display: 'flex',
          alignItems: 'center',
          justifyContent: 'center',
          color: '#ffc107',
          mr: 1,
          '&:hover': {
            backgroundColor: '#f0f0f0',
          },
        }}
      >
        <AddRoundedIcon fontSize="small" />
      </IconButton>
      <Typography variant="body1" sx={{ color: '#333', fontSize: FONT_SIZE }}>
        새 프로젝트
      </Typography>
    </Box>

    {mockProjects.map((project) => (
      <Box
        key={project.id}
        display="flex"
        alignItems="center"
        justifyContent="space-between"
        mb={0.5}
      >
        <Box display="flex" alignItems="center">
          <Box
            sx={{
              width: ICON_SIZE,
              height: ICON_SIZE,
              backgroundColor: '#ffc107',
              borderRadius: '8px',
              mr: 1,
            }}
          />
          <ButtonBase
            sx={{
              textAlign: 'left',
              color: '#333',
            }}
          >
            <Typography variant="body1" sx={{ fontSize: FONT_SIZE }}>
              {project.title}
            </Typography>
          </ButtonBase>
        </Box>

        <Stack direction="row" spacing={0.5}>
          <IconButton sx={{ color: '#9e9e9e' }} aria-label="edit">
            <EditIcon fontSize="small" />
          </IconButton>
          <IconButton sx={{ color: '#e53935' }} aria-label="delete">
            <DeleteIcon fontSize="small" />
          </IconButton>
        </Stack>
      </Box>
    ))}
  </Box>
);

export default ProjectList;
