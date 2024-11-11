import React from 'react';
import { Typography, List, ListItem, ListItemText } from '@mui/material';
import CircleIcon from '@mui/icons-material/Circle';

const agileNotes = [
  { id: 1, title: '효율적으로 회의하는 방법' },
  { id: 2, title: '애자일 방법론이란?' },
  { id: 3, title: '플래닝 포커란?' },
  { id: 4, title: '스프린트와 백로그에 대해..' },
];

const AgileNotesList = () => (
  <List>
    {agileNotes.map((note) => (
      <ListItem
        key={note.id}
        sx={{
          display: 'flex',
          alignItems: 'center',
          cursor: 'pointer',
          '&:hover': {
            backgroundColor: '#f0f0f0',
          },
        }}
        onClick={() => {}}
      >
        <CircleIcon sx={{ color: '#356B60', fontSize: '0.5rem', mr: 1 }} />
        <ListItemText
          primary={
            <Typography sx={{ fontSize: '1rem', color: '#333' }}>
              {note.title}
            </Typography>
          }
        />
      </ListItem>
    ))}
  </List>
);

export default AgileNotesList;
